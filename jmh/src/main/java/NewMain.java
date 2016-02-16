
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import org.openjdk.jmh.util.Deduplicator;
import org.openjdk.jmh.util.FileUtils;
import org.openjdk.jmh.util.Multiset;
import org.openjdk.jmh.util.TreeMultiset;

/**
 *
 * @author René Kraneis
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PerfEvents readEvents = new NewMain(args).readEvents(0);
        System.out.println(readEvents.toString());
    }
    private String perfParsedData;
    private String pid;
    private List<String> events;

    NewMain(String... args) {
        perfParsedData = args[0];
        pid = args[1];
        String[] argsn = Arrays.copyOfRange(args, 2, args.length);
        events = Arrays.asList(argsn);
    }

    PerfEvents readEvents(double skipSec) {
        FileReader fr = null;
        try {
            Deduplicator<String> dedup = new Deduplicator<String>();

            fr = new FileReader(perfParsedData);
            BufferedReader reader = new BufferedReader(fr);

            Map<Long, String> methods = new HashMap<Long, String>();
            Map<Long, String> libs = new HashMap<Long, String>();
            Map<String, Multiset<Long>> events = new LinkedHashMap<String, Multiset<Long>>();
            for (String evName : this.events) {
                events.put(evName, new TreeMultiset<Long>());
            }

            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                String[] elems = line.split(",");

                String evName = elems[0].trim();

                // We work with only one event type - "SampledProfile".
                if (!this.events.get(0).equals(evName)) {
                    continue;
                }

                // Check PID.
                String pidStr = elems[2].trim();

                int pidOpenIdx = pidStr.indexOf("(");
                int pidCloseIdx = pidStr.indexOf(")");

                if (pidOpenIdx == -1 || pidCloseIdx == -1 || pidCloseIdx < pidOpenIdx) {
                    continue; // Malformed PID, probably this is the header.
                }
                pidStr = pidStr.substring(pidOpenIdx + 1, pidCloseIdx).trim();

                if (!pid.equals(pidStr)) {
                    continue;
                }

                // Check timestamp
                String timeStr = elems[1].trim();

                double time = Double.valueOf(timeStr) / 1000000;

                if (time < skipSec) {
                    continue;
                }

                // Get address.
                String addrStr = elems[4].trim().replace("0x", "");

                // Get lib and function name.
                String libSymStr = elems[7].trim();

                String lib = libSymStr.substring(0, libSymStr.indexOf('!'));
                String symbol = libSymStr.substring(libSymStr.indexOf('!') + 1);

                Multiset<Long> evs = events.get(evName);

                assert evs != null;

                try {
                    Long addr = Long.valueOf(addrStr, 16);
                    evs.add(addr);
                    methods.put(addr, dedup.dedup(symbol));
                    libs.put(addr, dedup.dedup(lib));
                } catch (NumberFormatException e) {
                    // kernel addresses like "ffffffff810c1b00" overflow signed long,
                    // record them as dummy address
                    evs.add(0L);
                }
            }

            methods.put(0L, "<kernel>");

            return new PerfEvents(this.events, events, methods, libs);
        } catch (IOException e) {
            return new PerfEvents(events);
        } finally {
            FileUtils.safelyClose(fr);
        }
    }

    protected static class PerfEvents {

        final Map<String, Multiset<Long>> events;
        final Map<Long, String> methods;
        final Map<Long, String> libs;
        final Map<String, Long> totalCounts;

        PerfEvents(Collection<String> tracedEvents, Map<String, Multiset<Long>> events, Map<Long, String> methods, Map<Long, String> libs) {
            this.events = events;
            this.methods = methods;
            this.libs = libs;
            this.totalCounts = new HashMap<String, Long>();
            for (String event : tracedEvents) {
                totalCounts.put(event, events.get(event).size());
            }
        }

        public PerfEvents(Collection<String> tracedEvents) {
            this(tracedEvents, Collections.<String, Multiset<Long>>emptyMap(), Collections.<Long, String>emptyMap(), Collections.<Long, String>emptyMap());
        }

        public boolean isEmpty() {
            return events.isEmpty();
        }

        public Multiset<Long> get(String event) {
            return events.get(event);
        }

        public SortedSet<Long> getAllAddresses() {
            SortedSet<Long> addrs = new TreeSet<Long>();
            for (Multiset<Long> e : events.values()) {
                addrs.addAll(e.keys());
            }
            return addrs;
        }

        public Long getTotalEvents(String event) {
            return totalCounts.get(event);
        }

        @Override
        public String toString() {
            return "PerfEvents{"
                    + "events=" + events
                    + ", methods=" + methods
                    + ", libs=" + libs
                    + ", totalCounts=" + totalCounts
                    + '}';
        }

    }
}

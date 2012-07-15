package net.minecraft.src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Profiler
{
    /** Flag profiling enabled */
    public static boolean profilingEnabled = false;

    /** List of parent sections */
    private static List sectionList = new ArrayList();

    /** List of timestamps (System.nanoTime) */
    private static List timestampList = new ArrayList();

    /** Current profiling section */
    private static String profilingSection = "";

    /** Profiling map */
    private static Map profilingMap = new HashMap();

    /**
     * Start section
     */
    public static void startSection(String par0Str)
    {
        if (profilingEnabled)
        {
            if (profilingSection.length() > 0)
            {
                profilingSection = profilingSection + ".";
            }

            profilingSection = profilingSection + par0Str;
            sectionList.add(profilingSection);
            timestampList.add(Long.valueOf(System.nanoTime()));
        }
    }

    /**
     * End section
     */
    public static void endSection()
    {
        if (profilingEnabled)
        {
            long var0 = System.nanoTime();
            long var2 = ((Long)timestampList.remove(timestampList.size() - 1)).longValue();
            sectionList.remove(sectionList.size() - 1);
            long var4 = var0 - var2;

            if (profilingMap.containsKey(profilingSection))
            {
                profilingMap.put(profilingSection, Long.valueOf(((Long)profilingMap.get(profilingSection)).longValue() + var4));
            }
            else
            {
                profilingMap.put(profilingSection, Long.valueOf(var4));
            }

            profilingSection = sectionList.size() > 0 ? (String)sectionList.get(sectionList.size() - 1) : "";

            if (var4 > 100000000L)
            {
                System.out.println(profilingSection + " " + var4);
            }
        }
    }

    /**
     * End current section and start a new section
     */
    public static void endStartSection(String par0Str)
    {
        endSection();
        startSection(par0Str);
    }
}

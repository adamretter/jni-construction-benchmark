/**
 * Copyright © 2016, Evolved Binary Ltd
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the <organization> nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.evolvedbinary.jnibench.consbench;

import java.util.List;

public class BenchmarkHelper {

  public static void outputResults(final boolean outputAsCSV, final boolean noCsvHeader, final boolean inNs, final List<? extends BenchmarkFixture> benchmarkFixtures) {
    if (outputAsCSV) {
      final StringBuilder stringBuilder = new StringBuilder();

      // header
      if (!noCsvHeader) {
        for (int i = 0; i < benchmarkFixtures.size(); i++) {
          stringBuilder.append('"').append(benchmarkFixtures.get(i).getDescription()).append('"');
          if (i < benchmarkFixtures.size() - 1) {
            stringBuilder.append(',');
          }
        }
        stringBuilder.append(System.getProperty("line.separator"));
      }

      // data
      for (int i = 0; i < benchmarkFixtures.size(); i++) {
        stringBuilder.append(benchmarkFixtures.get(i).duration());
        if (i < benchmarkFixtures.size() - 1) {
          stringBuilder.append(',');
        }
      }

      System.out.println(stringBuilder.toString());
    } else {
      final String timeUnits = timeUnits(inNs);
      for (final BenchmarkFixture benchmarkFixture : benchmarkFixtures) {
        System.out.println(benchmarkFixture.getDescription() + ": " + benchmarkFixture.duration() + timeUnits);
      }
    }
  }

  public static long time(final boolean inNs) {
    if (inNs) {
      return System.nanoTime();
    } else {
      return System.currentTimeMillis();
    }
  }

  private static String timeUnits(final boolean inNs) {
    if (inNs) {
      return "ns";
    } else {
      return "ms";
    }
  }
}

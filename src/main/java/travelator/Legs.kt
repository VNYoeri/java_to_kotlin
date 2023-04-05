package travelator

import java.time.Duration

fun List<Leg>.longestLegOver(duration: Duration): Leg? {
    return maxByOrNull(Leg::plannedDuration)?.takeIf { it.plannedDuration > duration}
}
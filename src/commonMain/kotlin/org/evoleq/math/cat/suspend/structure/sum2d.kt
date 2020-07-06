package org.evoleq.math.cat.suspend.structure

import kotlinx.coroutines.CoroutineScope
import org.evoleq.math.cat.adt.Sum
import org.evoleq.math.cat.marker.MathCatDsl
import org.evoleq.math.cat.marker.MathSpeakDsl


@MathSpeakDsl
suspend operator fun <S1, T1,S2, T2> (suspend CoroutineScope.(S1)->T1).plus(other: suspend CoroutineScope.(S2)->T2): suspend CoroutineScope.(Sum<S1, S2>)-> Sum<T1, T2> = {
    sum -> when(sum) {
    is Sum.Summand1 -> Sum.Summand1(this@plus(sum.value))
    is Sum.Summand2 -> Sum.Summand2(other(sum.value))
}
}

@MathCatDsl
suspend fun <S1, S2, T> measure(measure1: suspend CoroutineScope.(S1)->T, measure2: suspend CoroutineScope.(S2)->T):suspend CoroutineScope.(Sum<S1, S2>)->T = { observed ->
    when (observed) {
        is Sum.Summand1 -> measure1(observed.value)
        is Sum.Summand2 -> measure2(observed.value)
    }
}
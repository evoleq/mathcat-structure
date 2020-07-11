/**
 * Copyright (c) 2020 Dr. Florian Schmidt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
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
package org.evoleq.math.cat.structure

import org.evoleq.math.cat.adt.Sum
import org.evoleq.math.cat.marker.MathCatDsl
import org.evoleq.math.cat.marker.MathSpeakDsl

@MathSpeakDsl
operator fun <S1, T1,S2, T2> ((S1)->T1).plus(other: (S2)->T2): (Sum<S1, S2>)->Sum<T1, T2> = {
    sum -> when(sum) {
        is Sum.Summand1 -> Sum.Summand1(this@plus(sum.value))
        is Sum.Summand2 -> Sum.Summand2(other(sum.value))
    }
}

@MathCatDsl
fun <S1, S2, T> measure(measure1: (S1)->T, measure2: (S2)->T):(Sum<S1, S2>)->T = { observed ->
    when (observed) {
        is Sum.Summand1 -> measure1(observed.value)
        is Sum.Summand2 -> measure2(observed.value)
    }
}
/*
@MathSpeakDsl
suspend operator fun <S1, T1,S2, T2> (suspend (S1)->T1).plus(other: suspend (S2)->T2): suspend (Sum<S1, S2>)->Sum<T1, T2> = {
    sum -> when(sum) {
        is Sum.Summand1 -> Sum.Summand1(this@plus(sum.value))
        is Sum.Summand2 -> Sum.Summand2(other(sum.value))
    }
}

 */
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

import kotlinx.coroutines.CoroutineScope
import org.evoleq.math.cat.marker.MathCatDsl

@MathCatDsl
infix fun <S, T> S.x(other: T): Pair<S, T> = Pair(this, other)
@MathCatDsl
infix fun <S1,T1,S2,T2> ((S1)->T1).x(other: (S2)->T2): (Pair<S1,S2>)->Pair<T1,T2> = {
    pair -> this(pair.first) x other(pair.second)
}
@MathCatDsl
infix fun <S1,T1,S2,T2> (suspend (S1)->T1).x(other: suspend (S2)->T2): suspend (Pair<S1,S2>)->Pair<T1,T2> = {
    pair -> this(pair.first) x other(pair.second)
}
@MathCatDsl
infix fun <S1,T1,S2,T2> (suspend CoroutineScope.(S1)->T1).x(other: suspend CoroutineScope.(S2)->T2): suspend CoroutineScope.(Pair<S1,S2>)->Pair<T1,T2> = {
    pair -> this@x(pair.first) x other(pair.second)
}

@MathCatDsl
fun <R, S, T> tau():(Pair<R, Pair<S, T>>) -> Pair<S, Pair<R, T>> = {pair ->
    pair.second.first x (pair.first x pair.second.second)
}
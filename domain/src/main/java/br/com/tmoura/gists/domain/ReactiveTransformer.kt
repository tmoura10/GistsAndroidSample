package br.com.tmoura.gists.domain

import io.reactivex.CompletableTransformer
import io.reactivex.FlowableTransformer
import io.reactivex.MaybeTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.SingleTransformer

interface ReactiveTransformer<T>:
        SingleTransformer<T, T>,
        CompletableTransformer,
        FlowableTransformer<T, T>,
        ObservableTransformer<T, T>,
        MaybeTransformer<T, T>
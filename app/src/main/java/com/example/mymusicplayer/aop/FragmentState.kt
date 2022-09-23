package com.example.mymusicplayer.aop

import android.util.Log
import androidx.fragment.app.Fragment
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut

/**
 * Copyright (c) 2022 Raysharp.cn. All rights reserved
 *
 * FragmentState
 * @author longyanghe
 * @date 2022-04-18
 */
@Aspect
class FragmentState {

    @After("execution(* androidx.fragment.app.Fragment.on**(..))")
    @Throws(Throwable::class)
    fun fragmentMethod(joinPoint: JoinPoint) {
        Log.i("helloAOP", "aspect:::" + joinPoint.signature)
    }

//    @Around("execution(* androidx.fragment.app.Fragment.on**(..))")
//    @Throws(Throwable::class)
//    fun aroundJoinPoint(joinPoint: ProceedingJoinPoint){
//        joinPoint.proceed()
//        val fragment = joinPoint.getThis() as Fragment
//        Log.e(
//            "helloAOP",
//            "the Fragment is :" + fragment.tag + ", method is :" + joinPoint
//        )
//    }
}
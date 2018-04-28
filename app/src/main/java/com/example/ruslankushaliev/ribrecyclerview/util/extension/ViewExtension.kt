package com.example.ruslankushaliev.ribrecyclerview.util.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View =
        LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false, initView: View.() -> Unit): View =
        LayoutInflater.from(context).inflate(layoutId, this, attachToRoot).apply { this.initView() }

@Suppress("UNCHECKED_CAST")
fun <T> ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): T =
        LayoutInflater.from(context).inflate(layoutId, this, attachToRoot) as T

@Suppress("UNCHECKED_CAST")
fun <T> ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false, initView: View.() -> Unit): T =
        LayoutInflater.from(context).inflate(layoutId, this, attachToRoot).apply { this.initView() } as T
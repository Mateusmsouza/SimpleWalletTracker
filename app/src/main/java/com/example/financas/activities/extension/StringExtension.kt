package com.example.financas.activities.extension

import kotlin.String as string

fun string.sliceIfHasMoreSizeThan(limitSize: Int): string {
    if (this.length > limitSize) {
        return "${this.substring(0, limitSize)}..."
    }

    return this
}
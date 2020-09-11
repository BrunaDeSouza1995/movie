package com.bruna.movie.extension

import org.junit.Assert

infix fun Any.assertEquals(another: Any) {
    Assert.assertEquals(this, another)
}

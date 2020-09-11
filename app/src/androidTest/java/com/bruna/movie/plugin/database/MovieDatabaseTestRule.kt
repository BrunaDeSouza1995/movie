package com.bruna.movie.plugin.database

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class MovieDatabaseTestRule : TestRule {
    var database: MovieDatabase? = null

    override fun apply(base: Statement?, description: Description?): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                createDatabase()
                try {
                    base?.evaluate()
                } finally {
                    closeDatabase()
                }
            }
        }
    }

    private fun createDatabase() {
        val context = InstrumentationRegistry.getInstrumentation().context

        database = Room.inMemoryDatabaseBuilder(context, MovieDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    private fun closeDatabase() {
        database?.close()
    }
}

package com.kozan.utils


/*
@Module
@InstallIn(SingletonComponent::class)
object MainModule {


    val migration_6_7 = object : Migration(6, 7) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE testQuizzes ADD COLUMN solution TEXT")
        }
    }

    // val solution : String to val solution : String?
    val migration_7_8 = object : Migration(7, 8) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE TABLE testQuizzes_temp (quizId INTEGER NOT NULL, displayId INTEGER NOT NULL, questionId INTEGER NOT NULL, question TEXT NOT NULL, options TEXT NOT NULL, correctPosition INTEGER NOT NULL, resultType TEXT, solution TEXT, roomId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)")
            database.execSQL("INSERT INTO testQuizzes_temp (quizId, displayId, questionId, question, options, correctPosition, resultType, solution, roomId) SELECT quizId, displayId, questionId, question, options, correctPosition, resultType, solution, roomId FROM testQuizzes")
            database.execSQL("DROP TABLE testQuizzes")
            database.execSQL("CREATE TABLE testQuizzes (quizId INTEGER NOT NULL, displayId INTEGER NOT NULL, questionId INTEGER NOT NULL, question TEXT NOT NULL, options TEXT NOT NULL, correctPosition INTEGER NOT NULL, resultType TEXT, solution TEXT, roomId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)")
            database.execSQL("INSERT INTO testQuizzes (quizId, displayId, questionId, question, options, correctPosition, resultType, solution, roomId) SELECT quizId, displayId, questionId, question, options, correctPosition, resultType, solution, roomId FROM testQuizzes_temp")
            database.execSQL("DROP TABLE testQuizzes_temp")
        }
    }

    val migration_8_9 = object : Migration(8, 9) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("""
            CREATE TABLE completedHomeworks (
                roomId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                id INTEGER NOT NULL,
                level TEXT NOT NULL
            )
        """)
        }
    }


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext app: Context) =
        Room.databaseBuilder(app, AppDatabase::class.java, "content-db")
            .fallbackToDestructiveMigrationFrom(1, 2, 3, 4, 5)
            .addMigrations(migration_6_7, migration_7_8, migration_8_9)
            .build()

    @Provides
    @Singleton
    fun getDao(db: AppDatabase) = db.mainDao()


    @Provides
    @Singleton
    fun getPreferencesHelper(@ApplicationContext app: Context) = PreferenceHelper(app)

    @Provides
    @Singleton
    fun getJsonHelper(@ApplicationContext app: Context) = JsonHelper(app)


}*/

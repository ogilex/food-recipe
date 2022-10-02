package rs.raf.projekat2.ognjen_boskovic_rn9518.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import rs.raf.projekat2.ognjen_boskovic_rn9518.modules.coreModule
import rs.raf.projekat2.ognjen_boskovic_rn9518.modules.recipeModule
import rs.raf.projekat2.ognjen_boskovic_rn9518.modules.userModule
import timber.log.Timber

class FoodRecipeApp : Application() {

    override fun onCreate() {
        super.onCreate()
        init();
    }

    private fun init() {
        initTimber()
        initKoin()
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

    private fun initKoin() {
        val modules = listOf(
            coreModule,
            userModule,
            recipeModule
        )
        startKoin {
            androidLogger(Level.ERROR)
            // Use application context
            androidContext(this@FoodRecipeApp)
            // Use properties from assets/koin.properties
            androidFileProperties()
            // Use koin fragment factory for fragment instantiation
            fragmentFactory()
            // modules
            modules(modules)
        }
    }
}
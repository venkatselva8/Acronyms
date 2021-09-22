package task.acronyms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import task.acronyms.ui.AcronymFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, AcronymFragment.newInstance())
                .commitNow()
        }
    }
}
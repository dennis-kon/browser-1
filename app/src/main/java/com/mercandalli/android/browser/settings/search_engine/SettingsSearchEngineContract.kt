package com.mercandalli.android.browser.settings.search_engine

import androidx.annotation.ColorRes
import com.mercandalli.android.browser.search_engine.SearchEngine
import com.mercandalli.android.browser.in_app.InAppManager

interface SettingsSearchEngineContract {

    interface Screen {

        fun setSectionColor(@ColorRes sectionColorRes: Int)

        fun setTextPrimaryColorRes(@ColorRes textPrimaryColorRes: Int)

        fun setTextSecondaryColorRes(@ColorRes textSecondaryColorRes: Int)

        fun showSearchEngineUnlockRow()

        fun hideSearchEngineUnlockRow()

        fun showSearchEngineRow()

        fun hideSearchEngineRow()

        fun showSearchEngineSection()

        fun hideSearchEngineSection()

        fun showSearchEngineSectionLabel()

        fun hideSearchEngineSectionLabel()

        fun showSearchEngineSelection(searchEngines: List<SearchEngine>)

        fun displaySearchEngine(searchEngineName: String)
    }

    interface UserAction {

        fun onAttached()

        fun onDetached()

        fun onSearchEngineRowClicked()

        fun onSearchEngineUnlockRowClicked(activityContainer: InAppManager.ActivityContainer)
    }
}
package guideme.volunteers.ui.fragments.base

class FragmentConfiguration(val toolbar: ToolbarConfiguration = ToolbarConfiguration(),
                            var layoutResourceId: Int? = null) {
    companion object {
        fun withLayout(layoutResourceId: Int): Builder {
            return Builder().withLayout(layoutResourceId)
        }

        fun withTitle(titleResourceId: Int): Builder {
            return Builder().title(titleResourceId)
        }
    }

    class Builder {
        private val configuration = FragmentConfiguration()
        fun withLayout(layoutResourceId: Int): Builder {
            configuration.layoutResourceId = layoutResourceId
            return this
        }

        fun title(titleResourceId: Int): Builder {
            configuration.toolbar.titleResourceId = titleResourceId
            return this
        }

        fun showBackArrow(): Builder {
            configuration.toolbar.showBackArrow = true
            return this
        }

        fun create(): FragmentConfiguration {
            return FragmentConfiguration(configuration.toolbar, configuration.layoutResourceId)
        }

        fun noArrow(): Builder {
            configuration.toolbar.showBackArrow = false
            return this
        }
    }
}
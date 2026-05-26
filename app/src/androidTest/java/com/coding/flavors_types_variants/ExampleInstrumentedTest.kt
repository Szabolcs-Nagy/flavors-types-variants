package com.coding.flavors_types_variants

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.runner.RunWith
import org.junit.Rule
import org.junit.Test

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun variantInspector_displaysBuildConfigValues() {
        composeRule.onNodeWithText("demo").fetchSemanticsNode()
        composeRule.onNodeWithText("sandbox").fetchSemanticsNode()
        composeRule.onNodeWithText("debug").fetchSemanticsNode()
        composeRule.onNodeWithText("[DEBUG]", substring = true).fetchSemanticsNode()
        composeRule.onNodeWithTag("resolvedVariantRow").fetchSemanticsNode()
        composeRule.onNodeWithText("demoSandboxDebug").fetchSemanticsNode()
    }

    @Test
    fun variantInspector_displaysAllVariantCombinationsMatrix() {
        composeRule.onNodeWithTag("variantMatrix").fetchSemanticsNode()
        composeRule.onNodeWithText("All possible variants (12)").fetchSemanticsNode()
        composeRule.onNodeWithText("Build type color legend").fetchSemanticsNode()
    }
}
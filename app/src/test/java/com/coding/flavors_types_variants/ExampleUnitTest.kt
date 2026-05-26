package com.coding.flavors_types_variants

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleUnitTest {
    @Test
    fun resolveVariantName_composesFlavorAndBuildType() {
        assertEquals("demoSandboxDebug", resolveVariantName("demoSandbox", "debug"))
    }

    @Test
    fun allVariantNames_containsTwelveUniqueVariants() {
        val variants = allVariantNames()

        assertEquals(12, variants.size)
        assertEquals(12, variants.toSet().size)
        assertTrue(variants.contains("demoSandboxDebug"))
        assertTrue(variants.contains("fullProductionRelease"))
    }
}
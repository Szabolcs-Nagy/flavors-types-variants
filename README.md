# Flavors, Build Types, and Variants Demo

This sample illustrates:

`build variant = product flavor(s) + build type`

## Project matrix

- Flavor dimensions:
  - `tier`: `demo`, `full`
  - `environment`: `sandbox`, `production`
- Build types:
  - `debug`, `staging`, `release`

Total variants: `2 x 2 x 3 = 12`

Examples: `demoSandboxDebug`, `demoProductionStaging`, `fullProductionRelease`

## Build type differences

- `debug`: used for day-to-day development and local troubleshooting.
- `staging`: used for pre-release validation (QA/UAT) before production rollout.
- `release`: used for production-ready distribution.

In this project specifically:

- `debug` is the development baseline.
- `staging` is created with `initWith(getByName("debug"))`, then separated via `applicationIdSuffix = ".staging"` and `versionNameSuffix = "-staging"`.
- `release` is the production-oriented build type.

Simple rule of thumb: `debug` = build while developing, `staging` = test before shipping, `release` = ship to users.

## Where to check

- `app/build.gradle.kts` - flavors + build types setup
- `app/src/main/java/com/coding/flavors_types_variants/MainActivity.kt` - runtime variant info UI, build-type color legend, variant badge color, and full 12-item matrix
- `app/src/androidTest/java/com/coding/flavors_types_variants/ExampleInstrumentedTest.kt` - checks that BuildConfig values appear in the UI
- `app/src/test/java/com/coding/flavors_types_variants/ExampleUnitTest.kt` - checks variant-name composition and full matrix generation

## Try

```bash
./gradlew :app:assembleDemoSandboxDebug
./gradlew :app:assembleFullProductionRelease
```

## Verify tests

```bash
./gradlew :app:testDemoSandboxDebugUnitTest
./gradlew :app:assembleDemoSandboxDebugAndroidTest
```

To execute instrumentation tests on a running emulator/device:

```bash
./gradlew :app:connectedDemoSandboxDebugAndroidTest
```

The active badge includes an explicit build-type token (`[DEBUG]`, `[STAGING]`, `[RELEASE]`) so the badge and legend are directly correlated.


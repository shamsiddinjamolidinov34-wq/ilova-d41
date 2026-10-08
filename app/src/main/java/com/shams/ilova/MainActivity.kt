name: Build thaildanbileti APK
on: push: branches: [main] workflow_dispatch:
permissions: contents: read
jobs: build: runs-on: ubuntu-latest
steps:
  - name: Download source code
    uses: actions/checkout@v5

  - name: Install Java
    uses: actions/setup-java@v5
    with:
      distribution: temurin
      java-version: '17'

  - name: Build APK
    run: |
      chmod +x gradlew
      ./gradlew assembleDebug
      cp app/build/outputs/apk/debug/app-debug.apk thaildanbileti.apk

  - name: Upload APK
    uses: actions/upload-artifact@v4
    with:
      name: thaildanbileti
      path: thaildanbileti.apk
      if-no-files-found: error

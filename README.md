<h1 align="center">
<br>
  <img src="screenshots/kotlin.png" width="300" alt="Kotlin Pokedex">
<br>
<br>
Pokedex app built with Kotlin
</h1>

<p align="center">
  <a href="https://github.com/KotlinBy/awesome-kotlin">
    <img src="https://kotlin.link/awesome-kotlin.svg" alt="Awesome Kotlin">
  </a>
  
  <a href="https://github.com/rodrigosambadesaa/Kotlin-Pokedex/actions">
    <img src="https://github.com/rodrigosambadesaa/Kotlin-Pokedex/workflows/Publish%20APK%20release/badge.svg" alt="Publish APK release">
  </a>
  
  <a href="https://opensource.org/licenses/MIT">
    <img src="https://img.shields.io/badge/License-MIT-red.svg" alt="License MIT">
  </a>
  
  <a href="https://github.com/rodrigosambadesaa/Kotlin-Pokedex/stargazers">
    <img src="https://img.shields.io/badge/Say%20Thanks-👍-1EAEDB.svg" alt="Say thanks">
  </a>
</p>

## Download

Go to the [releases page](https://github.com/rodrigosambadesaa/Kotlin-Pokedex/releases) to download the latest available apk.

## Connectivity policy

Before starting a network operation, the app performs cheap local checks using both
`ConnectivityAndInternetAccess.isConnected()` and `hasPhysicalNetwork()` (or the passive
`NetworkObserver` state). This prevents a dangling VPN-only network from being treated as
Internet connectivity. When no usable physical network exists, network work is skipped and
the local Pokémon asset is used.
When a network exists, the app starts the real Retrofit or Glide operation directly; those
operations retain their own timeouts and exception handling. A general active connectivity
diagnostic is run only after a network-shaped failure such as DNS, route, timeout, or TLS
failure. HTTP responses are handled as service responses and do not trigger that diagnostic.

<!--
Also available in Play Store

<a href="https://github.com/mrcsxsiq/Kotlin-Pokedex/">
  <img src="https://play.google.com/intl/en_us/badges/static/images/badges/en_badge_web_generic.png" width="200" alt="Play Store">
</a>
-->

## Screenshots

<p align="center">
  <img src="screenshots/home.png" width="250" alt="Home">
  <img src="screenshots/pokedex.png" width="250" alt="Pokedex">
  <img src="screenshots/pokedex-fab.png" width="250" alt="Pokedex FAB">
</p>

<p align="center">
  <img src="screenshots/pokedex-fab-search.png" width="250" alt="Pokedex Search">
  <img src="screenshots/pokedex-fab-generation.png" width="250" alt="Pokedex Generation">
  <img src="screenshots/pokemon-info-about.png" width="250" alt="Pokemon Info - About">
</p>

<p align="center">
  <img src="screenshots/pokemon-info-base-stats.png" width="250" alt="Pokemon Info - Base Stats">
  <img src="screenshots/pokemon-info-evolution.png" width="250" alt="Pokemon Info - Evolution">
  <img src="screenshots/news-detail.png" width="250" alt="News Detail">
</p>

## Development Roadmap

- [x] [Kotlin](https://kotlinlang.org/)
- [x] [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
- [x] [Navigation](https://developer.android.com/topic/libraries/architecture/navigation)
- [x] [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
- [x] [Room](https://developer.android.com/topic/libraries/architecture/room)
- [ ] [Coroutines](https://developer.android.com/topic/libraries/architecture/coroutines)
- [x] [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html)
- [x] [Databinding](https://developer.android.com/topic/libraries/data-binding)
- [x] [Retrofit](https://square.github.io/retrofit/)
- [x] [Koin](https://insert-koin.io/)
- [x] [Ktlint](https://ktlint.github.io/)
- [ ] JUnit
- [ ] MotionLayout
- [ ] Transition Animations
- [ ] DayNight
- [ ] PokeAPI
- [ ] ~[Jetpack Compose](https://developer.android.com/jetpack/compose)~ - See [compose-pokedex](https://github.com/zsoltk/compose-pokedex)

## Features

- [x] Home
- [x] Pokedex
- [x] Pokedex - FAB
- [x] Pokedex - Search
- [x] Pokedex - Generation
- [x] Pokemon Info
- [x] Pokemon Info - About
- [x] Pokemon Info - Base Stats
- [x] Pokemon Info - Evolution
- [x] News Detail

## Thanks

- [Márton Braun](https://github.com/zsmb13) for his [article](https://zsmb.co/lets-review-pokedex/) and [code review](https://github.com/mrcsxsiq/Kotlin-Pokedex/pull/3)

## Design

- [Saepul Nahwan](https://dribbble.com/saepulnahwan23) for his [Pokedex App design](https://dribbble.com/shots/6545819-Pokedex-App)

## Other Pokedex Projects

- [Zsolt Kocsi](https://github.com/zsoltk/compose-pokedex) - Android on Jetpack Compose
- [Pham Sy Hung](https://github.com/scitbiz/flutter_pokedex/) - Flutter


## Contributors

<a href="https://github.com/mrcsxsiq/Kotlin-Pokedex/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=mrcsxsiq/Kotlin-Pokedex&max=100" />
</a>

## License

All the code available under the MIT license. See [LICENSE](LICENSE).

```
MIT License

Copyright (c) 2019 Marcos Paulo Farias

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

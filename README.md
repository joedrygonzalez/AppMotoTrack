# AppMotoTrack 🏍️

**AppMotoTrack** és una aplicació nativa d'Android desenvolupada en Kotlin per al seguiment, control de manteniment, despeses i recordatoris de motocicletes.

---

## 📋 Taula de Comprovació de Requisits (Checklist)

| Requisit | Estat | Descripció / Fitxer d'Implementació |
| :--- | :---: | :--- |
| **Activities vs Fragments** | ✅ Complert | **Activities**: `IniciActivity`, `LoginActivity`, `SignActivity`, `MainActivity`.<br>**Fragments**: `HomeFragment`, `MotorcycleFragment`, `RemindersFragment`, `StatsFragment`, `SettingsFragment`, `HelpFragment`. |
| **Com navegar per les pantalles** | ✅ Complert | Navegació entre Activities mitjançant `Intent` i entre Fragments mitjançant `FragmentTransaction.replace()` des de `MainActivity.kt`. |
| **SplashScreen** | ✅ Complert | Integració de `androidx.core:core-splashscreen` en `IniciActivity` amb `installSplashScreen()` i tema `Theme.App.SplashScreen`. |
| **Bottom Menú** | ✅ Complert | Menú de navegació inferior personalitzat a `activity_main.xml` sincronitzat dinàmicament des de `MainActivity.kt`. |
| **Toolbar / Appbar** | ✅ Complert | Capçaleres personalitzades en vistes com `SettingsFragment`, `IniciActivity` i `MainActivity`. |
| **Quadres de diàleg (Dialogs)** | ✅ Complert | `AlertDialog` configurat a `SettingsFragment.kt` (`showLogoutDialog()`) amb `AlertDialog.Builder`. |
| **RecyclerView i les seves classes** | ✅ Complert | `RecyclerView` integrat a `fragment_remainders.xml` i `fragment_motorcycle.xml` usant la plantilla `item_reminder.xml`. |
| **Adapter i Holder** | ✅ Complert | Implementació a `RemindersAdapter.kt` amb la classe interna `ReminderViewHolder`. |
| **Llenguatge Kotlin: Dataclass** | ✅ Complert | `data class Reminder` definida a `Reminder.kt`. |
| **Llenguatge Kotlin: Object** | ✅ Complert | Singleton `object DateUtils` definit a `DateUtils.kt`. |
| **Llenguatge Kotlin: Extension Functions** | ✅ Complert | `Date.toFormattedString()` i `String.toCapitalized()` a `DateUtils.kt`. |

---

## 🛠️ Arquitectura del Projecte

```text
app/src/main/java/iticbcn/appmototrack/
├── MainActivity.kt               # Contenidor principal de fragments i navegació inferior
├── IniciActivity.kt              # Pantalla d'inici amb Splash Screen i opcions d'accés
├── LoginActivity.kt              # Pantalla de login d'usuari
├── SignActivity.kt               # Pantalla de registre d'usuari
├── adapters/
│   └── RemindersAdapter.kt       # RecyclerView.Adapter i ViewHolder per als recordatoris
├── fragments/
│   ├── HomeFragment.kt           # Menú principal amb 4 CardViews d'accés directe i Help
│   ├── MotorcycleFragment.kt     # Estat de la moto, KMs i recordatoris amb RecyclerView
│   ├── RemindersFragment.kt     # Llista completa de recordatoris
│   ├── StatsFragment.kt          # Estadístiques i resum de despeses
│   ├── SettingsFragment.kt       # Configuració i diàleg de tancament de sessió
│   └── HelpFragment.kt           # Secció d'ajuda i onboarding
├── models/
│   └── Reminder.kt               # Data Class del model de dades
└── utils/
    └── DateUtils.kt              # Object singleton i funcions d'extensió (Date.toFormattedString)
```

---
# Documentació Tècnica d'AppMotoTrack 

**AppMotoTrack** és una aplicació nativa Android desenvolupada en Kotlin per al seguiment de manteniments, control de quilometratge, gestió de despeses i recordatoris per a motocicletes.

---

## ️ Flux d'Usuari i Arquitectura del Projecte

El projecte s'organitza en una arquitectura basada en **Activities** per a la fase d'autenticació i **Fragments** per a la navegació del menú principal.

```text
app/src/main/java/iticbcn/appmototrack/
├── IniciActivity.kt          # Splash Screen i botons d'entrada (Login / Registre)
├── LoginActivity.kt          # Formulari d'inici de sessió amb validació visual
├── SignActivity.kt           # Formulari de registre d'usuari
├── MainActivity.kt           # Contenidor principal (FragmentContainerView + BottomMenu)
├── adapters/
│   └── RemindersAdapter.kt   # Adaptador de RecyclerView per als recordatoris
├── fragments/
│   ├── HomeFragment.kt       # Panell principal amb accés ràpid per CardViews
│   ├── MotorcycleFragment.kt # Estat de la motocicleta, KMs totals, Trips A/B i recordatoris
│   ├── RemindersFragment.kt # Llista interactiva de recordatoris
│   ├── StatsFragment.kt      # Resum de despeses per categories i períodes
│   ├── SettingsFragment.kt   # Configuració de l'aplicació i tancament de sessió
│   └── HelpFragment.kt       # Pantalla d'ajuda i onboarding
├── models/
│   └── Reminder.kt           # Data class del model de dades de recordatoris
└── utils/
    └── DateUtils.kt          # Object singleton i funcions d'extensió per a dates i text
```

---

##  Explicació Detallada de Pantalles i Codi

### 1. Cicle d'Inici i Autenticació
* **`IniciActivity`**: 
  - Inicialitza la Splash Screen nativa d'Android (`installSplashScreen()`) mantenint la pantalla de càrrega durant 3 segons (`setKeepOnScreenCondition`).
  - Disposa dels botons `btn_login` i `btn_sign` que inicien les activitats `LoginActivity` i `SignActivity` mitjançant `Intent`.
* **`LoginActivity` i `SignActivity`**:
  - Utilitzen camps d'entrada personalitzats (`custom_input.xml`) amb icones integrades (`custom_email_icon`, `custom_lock_icon`, `custom_eye_icon`, `custom_user_icon`).

### 2. Contenidor Principal i Navegació (`MainActivity`)
* Conté el contenidor de fragments `fragmentContainer` (`FragmentContainerView`) i el menú inferior `BottomMenu` (`LinearLayout`).
* El menú inferior inclou 5 botons (`BtnReminders`, `BtnStats`, `BtnHome`, `BtnMotos`, `BtnAccount`) amb els seus respectius `TextView` i icones superiors (`drawableTopCompat`).
* La funció `selectFragment(fragment, selectedIndex)` s'encarrega d'executar el reemplaçament de fragments en `supportFragmentManager` i d'actualitzar l'estat seleccionat (`buttons[i].isSelected`) per aplicar els colors d'estat (`custom_input_main.xml` i `custom_bottom_menu_color.xml`).

### 3. Pantalles Principals (Fragments)

####  `HomeFragment`
* Mostra una imatge de capçalera `MotoSlideImg` (`foto_fondo.jpg` optimitzada a 1200x800 px) amb la cantonada inferior arrodonida (`@style/FondoRedondeado`).
* Inclou el botó d'ajuda `CV_help` (`@id/IconMenuHelp`) que permet navegar directament cap a `HelpFragment`.
* Inclou 4 targetes principals (`CardView`):
  - `CV_StatsMainMenu` ➔ Navega a `StatsFragment` (Estadístiques).
  - `CV_RemainderMainMenu` ➔ Navega a `RemindersFragment` (Recordatoris).
  - `CV_SettingsMainMenu` ➔ Navega a `SettingsFragment` (Configuració).
  - `CV_MotorcycleMainMenu` ➔ Navega a `MotorcycleFragment` (Moto).

####  `MotorcycleFragment`
* **Control de KMs i Trips**: Targetes `CV_KmMotorcycle` (KMs totals) i `CV_TripMotorcycle` (parcials `TripAMotorcycle` i `TripBMotorcycle`).
* **Informació de Canvis**: Targeta `CV_MotorcycleInfo` amb el control de canvis d'oli (`CV_MotorcycleInfoOil`), líquid de fre darrere (`CV_MotorcycleInfoBrakeFluidRear`) i líquid de fre davant (`CV_MotorcycleInfoBrakeFluidFront`).
* **Recordatoris integrats**: Utilitza un `RecyclerView` (`@id/rvMotorcycleReminders`) connectat a `RemindersAdapter`.
* **Accions**: Botons `CV_help` (obrir ajuda), `CV_edit` (editar dades) i `CV_ChangeMotorcycle` (canviar de moto).

####  `RemindersFragment`
* Mostra la llista completa de recordatoris utilitzant un `RecyclerView` (`@id/rvReminders`) configurat amb `LinearLayoutManager`.
* Utilitza la plantilla visual `item_reminder.xml` que inclou la icona d'afegir al calendari (`ic_calendar_add_on`) i el text formatejat del recordatori.

####  `StatsFragment`
* Selector de període per mes i any (`CV_Month`, `CV_Year`).
* Targeta de resum (`cvSummary`) amb el total acumulat (`tvTotalSpentValue`) i la despesa del període seleccionat (`tvMonthlySpentValue`).
* Desglossament per categories mitjançant targetes `CardView`: Combustible (`cvCatFuel`), Manteniment (`cvCatMaintenance`), Assegurança (`cvCatInsurance`), Reparatge (`cvCatRepairs`), Pneumàtics (`cvCatTires`), Oli (`cvCatOil`), Recordatoris (`cvCatReminders`) i General (`cvCatGeneral`).

#### ️ `SettingsFragment`
* Capçalera amb botó enrere `btnBack` (`ic_arrow_back`). En prémer el botó enrere o accions de tancament, s'executa `showLogoutDialog()`, que mostra un diàleg de confirmació `AlertDialog` (`AlertDialog.Builder`) per tancar la sessió i tornar a `IniciActivity`.
* Inclou interruptors de configuració (`switchReviews`) i informació de la versió de l'aplicació.

####  `HelpFragment`
* Secció d'ajuda i onboarding amb la imatge `cvHelpImage`, el títol `tvHelpTitle` i la descripció `tvHelpDescription`.
* Disposa d'un indicador de pàgines (`layoutDotsIndicator`) i els botons `btnSkip` i `btnNext` per tornar al menú principal.

---

##  Model de Dades i Adaptador

* **`Reminder` (`data class`)**:
  ```kotlin
  data class Reminder(
      val id: Int,
      val title: String,
      val date: String = "",
      val category: String = "General"
  )
  ```
* **`RemindersAdapter` (`RecyclerView.Adapter`)**:
  - Utilitza la classe interna `ReminderViewHolder` per enllaçar els components visuals de `item_reminder.xml`.
  - Aplica les funcions d'extensió `String.toCapitalized()` per al títol i `Date.toFormattedString()` / `DateUtils.getCurrentDateFormatted()` per formatejar la data del recordatori.

* **`DateUtils` (`object`)**:
  - Singleton amb la constant `DEFAULT_DATE_FORMAT = "dd/MM/yyyy"` i mètodes utilitaris de dates.

---

##  Paleta de Colors i Estils

* **Colors Principals**:
  - Groc primari: `@color/yellow_primary` (`#FACC15`)
  - Blau primari: `@color/blue_primary` (`#2563EB`)
  - Fons clar: `@color/background_light` (`#F3F4F6`)
  - Fons fosc: `@color/background_dark` (`#111827`)
  - Superfície clara: `@color/surface_white` (`#FFFFFF`)
  - Superfície fosca: `@color/surface_dark` (`#1F2937`)

---

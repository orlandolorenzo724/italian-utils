# Italian Utilities Library

A Java library for managing Italian-specific data, including functionalities for Codice Fiscale, Partita IVA, IBAN, phone numbers, and various personal documents.

## Getting Started

This library is in its initial development phase. Contributions are welcome to build out and improve its features!

### Features

#### Dati Anagrafici: Italian Personal Data

The `AnagraficaUtils` class provides utility methods for handling and validating Italian personal data fields such as names, surnames, and titles. Below is a summary of the features implemented:

- **Name Validation**: `isValidName(String name)` – Validates a name, allowing only letters and spaces (suitable for handling middle names or compound names).
- **Surname Validation**: `isValidSurname(String surname)` – Validates a surname, allowing only letters without spaces.
- **Title Validation**: `isValidTitle(String title)` – Validates common Italian honorific titles such as "Sig.", "Sig.ra", and "Dott.".
- **Gender Validation**: `isValidGender(String gender)` – Validates gender based on Italian standards ("M" for male, "F" for female).
- **Age Calculation**: `calculateAge(LocalDate birthdate)` – Calculates the age of a person based on their birthdate.
- **Over 18 Check**: `isOver18(LocalDate birthdate)` – Checks if a person is 18 years old or older based on their birthdate.
- **Full Name Formatting**: `formatFullName(String title, String name, String surname)` – Formats a full name with optional title, name, and surname in proper Italian order.
- **Initials Extraction**: `getInitials(String name, String surname)` – Extracts and returns the initials from a given name and surname.
- **Name Normalization**: `normalizeName(String input)` – Capitalizes the first letter and makes the rest lowercase to standardize names and surnames.
- **Name Length Validation**: `isNameLengthValid(String name, int minLength, int maxLength)` – Checks if a name meets specified minimum and maximum length constraints.

#### Partita IVA: Italian VAT Number

The `PartitaIVAUtils` class offers methods to validate and format Italian VAT numbers (Partita IVA). The features include:

- **Partita IVA Validation**: `isValidPartitaIVA(String partitaIVA)` – Validates the structure and check digit of a given Partita IVA, ensuring it conforms to official standards.
- **Partita IVA Formatting**: `formatPartitaIVA(String partitaIVA)` – Formats a given Partita IVA by adding the "IT" country prefix, if not already present.

### Planned Features

The goal of this project is to provide a comprehensive toolkit for handling various Italian-specific data:

- **Codice Fiscale**: Calculation and validation of Italian tax codes.
- **IBAN and SWIFT**: Validation and formatting of banking information.
- **Phone Numbers**: Validation and formatting of Italian phone numbers.
- **Documents**: Handling Italian documents like identity cards, health cards, and driving licenses.

## Contributing

We welcome contributions! To start collaborating:

1. **Fork the repository** to your GitHub account.
2. **Create a new branch** following these naming conventions:
   - `feature/your-feature-name` for new features.
   - `bug/bug-fix-description` for bug fixes.
   - `improvement/improvement-description` for enhancements to existing code.
3. **Commit your changes** with clear commit messages.
4. **Submit a pull request** to have your changes reviewed.

If you have suggestions, feel free to open an issue to discuss it with the project maintainers.

## License

This project is licensed under the [MIT License](LICENSE), so you are free to use, modify, and distribute this library as you wish.

---

Let's make this a robust and easy-to-use library for anyone working with Italian data!
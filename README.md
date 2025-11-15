# Random Password Generator

A modern, secure, and feature-rich random password generator built with Java Swing. Designed for both casual users and
security professionals who need reliable password generation with advanced customization options.

![Java](https://img.shields.io/badge/Java-11%2B-blue)
![License](https://img.shields.io/badge/License-MIT-green)
![Maven](https://img.shields.io/badge/Build-Maven-orange)
![Windows](https://img.shields.io/badge/Platform-Windows%20EXE%20Ready-lightgrey)

## Features

### Security

- **Cryptographically Secure**: Uses Java's `SecureRandom` for true randomness
- **Custom Character Sets**: Choose from uppercase, lowercase, numbers, and symbols
- **Exclusion Options**: Option to exclude similar (i,l,1,L,o,0,O) and ambiguous characters
- **Entropy Control**: Adjustable entropy levels for different security needs

### Modern UI

- **Professional Interface**: Clean, modern Swing-based GUI
- **Real-time Feedback**: Password strength indicator with visual progress bar
- **One-Click Copy**: Clipboard integration for easy password transfer
- **Responsive Design**: Adapts to different screen sizes
- **Custom Icon**: Professional application icon in Windows

### Advanced Options

- **Custom Length**: Generate passwords from 8 to 128 characters
- **Character Control**: Fine-tune which character types to include
- **Strength Analysis**: Real-time password strength assessment
- **Batch Generation**: Quickly generate multiple passwords

## Quick Start

### Prerequisites

- Java 11 or later
- Maven 3.6+

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/chalwk/RandomPasswordGenerator.git
   cd RandomPasswordGenerator
   ```

2. **Build with Maven** (creates both JAR and EXE)
   ```bash
   mvn clean package
   ```

3. **Run the application**

   **Option 1: Run the JAR directly**
   ```bash
   java -jar target/RandomPasswordGenerator-1.0.0.jar
   ```

   **Option 2: Use the Windows EXE**
    - Navigate to `target/RandomPasswordGenerator-1.0.0.exe`
    - Double-click to run (no Java command line needed)

### Automated Windows Executable

The Maven build automatically generates a Windows `.exe` file using Launch4j:

- **Output**: `target/RandomPasswordGenerator-1.0.0.exe`
- **Icon**: Custom application icon included
- **JRE Requirement**: Java 11 or later
- **Build Command**: `mvn clean package`

The EXE file is a native Windows executable that bundles your Java application, making it easy to distribute and run
without command line knowledge.

## Usage

### Basic Password Generation

1. Launch the application (double-click the EXE or run the JAR)
2. Set your desired password length (default: 16 characters)
3. Select character types (uppercase, lowercase, numbers, symbols)
4. Click "Generate Password"
5. Use "Copy to Clipboard" to easily use the password

### Advanced Options

- **Exclude Similar Characters**: Avoid confusing characters like `i, l, 1, L, o, 0, O`
- **Exclude Ambiguous Characters**: Avoid characters that might be hard to distinguish
- **Entropy Control**: Adjust the randomness level using the slider
- **Strength Indicator**: Monitor password strength in real-time

### Example Configurations

**Strong Web Password** (Recommended)

- Length: 16-20 characters
- All character types enabled
- Exclude similar characters: Yes
- Strength: Strong (75-100%)

**PIN Code**

- Length: 4-6 characters
- Only numbers enabled
- Use for temporary access codes

**Memorable Password**

- Length: 12-16 characters
- Uppercase + lowercase + numbers
- Exclude ambiguous characters: Yes

## Project Structure

```
src/main/java/com/chalwk/
├── PasswordGenerator.java          # Main application entry point
├── ui/
│   ├── MainFrame.java              # Main application window
│   ├── PasswordPanel.java          # Password generation UI
│   └── components/
│       ├── ModernButton.java       # Custom styled buttons
│       └── ModernTextField.java    # Custom text fields
├── model/
│   └── PasswordConfig.java         # Password configuration model
└── util/
    ├── PasswordGeneratorEngine.java # Core password generation logic
    └── ClipboardUtil.java          # Clipboard integration

src/main/resources/
└── icon.ico                        # Application icon for Windows EXE
└── icon.png                        # Application icon for Windows EXE
```

## Development

### Building from Source

```bash
# Compile, package, and create EXE
mvn clean package

# Run tests
mvn test

# Run directly without packaging
mvn exec:java
```

### Dependencies

- **Java 11+**: Base requirement
- **Maven**: Build tool
- **Swing**: UI framework (included with Java)
- **Launch4j Maven Plugin**: Automated Windows EXE generation
- **Jackson** (optional): For future JSON configuration support

### Build Automation

The Maven build process now includes:

1. **Compilation**: Java source code compilation
2. **Packaging**: Creates executable JAR with proper manifest
3. **EXE Generation**: Automatically generates Windows executable using Launch4j
4. **Icon Integration**: Embeds custom application icon

### Code Architecture

The application follows MVC pattern:

- **Model**: `PasswordConfig` - Configuration data
- **View**: Swing UI components - User interface
- **Controller**: `PasswordGeneratorEngine` - Business logic

## Distribution

### For End Users

Simply distribute the `RandomPasswordGenerator-1.0.0.exe` file from the `target` directory. Users only need:

1. Java 11 or later installed on their system
2. The EXE file (double-click to run)

### For Developers

```bash
# Full build including EXE
mvn clean package

# Skip EXE generation (JAR only)
mvn clean package -DskipLaunch4j
```

## Security Considerations

- Uses cryptographically secure random number generation
- No network connectivity - completely offline
- No password storage or logging
- Generated passwords exist only in memory and clipboard

## Password Strength Guidelines

The application evaluates password strength based on:

- **Length**: Longer passwords are stronger
- **Character Variety**: Mix of character types increases security
- **Entropy**: Measure of randomness and unpredictability
- **Uniqueness**: Ratio of unique characters to total length

| Strength | Length | Character Types | Typical Use               |
|----------|--------|-----------------|---------------------------|
| Weak     | < 12   | 1-2 types       | Temporary, low-risk       |
| Fair     | 12-15  | 2-3 types       | Social media, forums      |
| Good     | 16-20  | 3-4 types       | Email, cloud services     |
| Strong   | 20+    | All types       | Banking, master passwords |

## Contributing

Contributions are welcome! Please feel free to submit pull requests or open issues for bugs and feature requests.

### Development Setup

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Built with Java Swing for cross-platform compatibility
- Maven for dependency management and build automation
- Launch4j Maven Plugin for automated Windows executable packaging
- Secure random number generation provided by Java Cryptography Architecture

## Support

If you encounter any issues or have questions:

1. Check the [Issues](https://github.com/chalwk/RandomPasswordGenerator/issues) page
2. Create a new issue with detailed description
3. Include your Java version and operating system

---

**Disclaimer**: This tool is provided for educational and practical purposes. Always follow your organization's security
policies and consider using a password manager for storing generated passwords securely.

---
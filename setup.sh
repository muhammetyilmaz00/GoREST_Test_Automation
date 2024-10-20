#!/bin/bash

# Set the project directory
PROJECT_DIR=$(pwd)

# Install Java
echo "Installing Java..."
if [[ "$(uname)" == "Darwin" ]]; then
  # macOS
  brew install openjdk@21 || { echo "Failed to install Java"; exit 1; }
elif [[ "$(uname -s)" == "Linux" ]]; then
  # Linux
  sudo apt-get update && sudo apt-get install -y openjdk-21-jdk || { echo "Failed to install Java"; exit 1; }
elif [[ "$(uname -s)" == "MINGW" || "$(uname -s)" == "CYGWIN" ]]; then
  # Windows
  choco install openjdk21 || { echo "Failed to install Java"; exit 1; }
else
  echo "Unsupported OS. Exiting.";
  exit 1;
fi

# Install Maven
echo "Installing Maven..."
if [[ "$(uname)" == "Darwin" ]]; then
  # macOS
  brew install maven || { echo "Failed to install Maven"; exit 1; }
elif [[ "$(uname -s)" == "Linux" ]]; then
  # Linux
  sudo apt-get update && sudo apt-get install -y maven || { echo "Failed to install Maven"; exit 1; }
elif [[ "$(uname -s)" == "MINGW" || "$(uname -s)" == "CYGWIN" ]]; then
  # Windows
  choco install maven || { echo "Failed to install Maven"; exit 1; }
fi

# Install dependencies
echo "Installing dependencies..."
mvn clean install || { echo "Failed to install dependencies"; exit 1; }

# Configure the environment
echo "Creating/Updating configuration file..."
if [ -f configuration.properties ]; then
  grep -q "token=" configuration.properties && sed -i "s/token=.*/token=${API_TOKEN}/" configuration.properties || echo "token=${API_TOKEN}" >> configuration.properties
else
  touch configuration.properties
  echo "token=${API_TOKEN}" >> configuration.properties
fi

# Set up the IDE (optional)
echo "Setting up the IDE (optional)..."
if [ -d "/Applications/IntelliJ IDEA.app" ]; then
  # IntelliJ IDEA on macOS
  /Applications/IntelliJ\ IDEA.app/Contents/MacOS/idea --import-projects $PROJECT_DIR
elif command -v eclipse >/dev/null 2>&1; then
  # Eclipse
  eclipse -import $PROJECT_DIR
elif command -v code >/dev/null 2>&1; then
  # Visual Studio Code
  code --install-extension ms-vscode.java
  code "$PROJECT_DIR"
fi

# Run the tests
echo "Running the tests..."
mvn verify || { echo "Tests failed"; exit 1; }

# Print a success message
echo "Setup complete!"
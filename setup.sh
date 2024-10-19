#!/bin/bash

# Set the project directory
PROJECT_DIR=$(pwd)

# Install Java
echo "Installing Java..."
if [ "$(uname)" == "Darwin" ]; then
  # macOS
  brew install openjdk@21
elif [ "$(expr substr $(uname -s) 1 5)" == "Linux" ]; then
  # Linux
  sudo apt-get update
  sudo apt-get install -y openjdk-21-jdk
elif [ "$(expr substr $(uname -s) 1 10)" == "MINGW32_NT" ]; then
  # Windows
  choco install openjdk21
fi

# Install Maven
echo "Installing Maven..."
if [ "$(uname)" == "Darwin" ]; then
  # macOS
  brew install maven
elif [ "$(expr substr $(uname -s) 1 5)" == "Linux" ]; then
  # Linux
  sudo apt-get update
  sudo apt-get install -y maven
elif [ "$(expr substr $(uname -s) 1 10)" == "MINGW32_NT" ]; then
  # Windows
  choco install maven
fi

# Install dependencies
echo "Installing dependencies..."
mvn clean install

# Configure the environment
echo "Creating configuration file..."
echo "token = your-api-token" > configuration.properties

# Set up the IDE (optional)
echo "Setting up the IDE (optional)..."
if [ -d "/Applications/IntelliJ IDEA.app" ]; then
  # IntelliJ IDEA on macOS
  /Applications/IntelliJ\ IDEA.app/Contents/MacOS/idea --import-projects $PROJECT_DIR
elif [ -d "/usr/local/bin/eclipse" ]; then
  # Eclipse on Linux
  /usr/local/bin/eclipse -import $PROJECT_DIR
elif [ -d "C:\Program Files\Visual Studio Code" ]; then
  # Visual Studio Code on Windows
  "C:\Program Files\Visual Studio Code\bin\code.cmd" --install-extension ms-vscode.java
fi

# Run the tests
echo "Running the tests..."
mvn verify

# Print a success message
echo "Setup complete!"
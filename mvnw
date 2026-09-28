#!/bin/sh
# ----------------------------------------------------------------------------
# Maven Wrapper Script for UNIX/Linux/macOS
# ----------------------------------------------------------------------------
DIR="$(cd "$(dirname "$0")" && pwd)"
MAVEN_VERSION="3.9.9"
MAVEN_HOME="$DIR/.tools/apache-maven-$MAVEN_VERSION"

if [ -f "$MAVEN_HOME/bin/mvn" ]; then
    exec "$MAVEN_HOME/bin/mvn" "$@"
fi

if command -v mvn >/dev/null 2>&1; then
    exec mvn "$@"
fi

echo "[VentureSeed] Maven not found. Downloading portable Apache Maven $MAVEN_VERSION..."
mkdir -p "$DIR/.tools"
curl -s -L "https://archive.apache.org/dist/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz" -o "$DIR/.tools/maven.tar.gz"
tar -xzf "$DIR/.tools/maven.tar.gz" -C "$DIR/.tools"
rm -f "$DIR/.tools/maven.tar.gz"

chmod +x "$MAVEN_HOME/bin/mvn"
exec "$MAVEN_HOME/bin/mvn" "$@"

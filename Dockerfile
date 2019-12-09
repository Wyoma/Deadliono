FROM gradle:4.10.0-jdk8

USER root

ENV SDK_URL="https://dl.google.com/android/repository/sdk-tools-linux-3859397.zip" \
ANDROID_HOME="/usr/local/android-sdk" \
ANDROID_VERSION=28 \
ANDROID_BUILD_TOOLS_VERSION=27.0.3

COPY ./src .

RUN mkdir "$ANDROID_HOME" .android \
&& cd "$ANDROID_HOME" \
&& curl -o sdk.zip $SDK_URL \
&& unzip sdk.zip \
&& rm sdk.zip \
&& yes | $ANDROID_HOME/tools/bin/sdkmanager --licenses

RUN $ANDROID_HOME/tools/bin/sdkmanager --update
RUN $ANDROID_HOME/tools/bin/sdkmanager "build-tools;28.0.3" \
"platforms;android-27" \
"platform-tools"

RUN apt-get update && apt-get install build-essential -y && apt-get install file -y && apt-get install apt-utils -y

CMD ["gradlew deadliono"]

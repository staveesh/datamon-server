# datamon-server

This server can be used to store and aggregate usage data from a predefined list of android applications.

## Prerequisites

Make sure that `docker` and `docker-compose` are installed on your host machine.

## Instructions

1. Clone this project:
   ```
   git clone https://github.com/staveesh/datamon-server.git
   ```

2. Change the current directory to `datamon-server
   ```
   cd datamon-server
   ```

3. Checkout the `lakay` branch.
   ```
   git checkout lakay
   ```

4. Create a `.env` file inside the current directory:

   ```
   touch .env
   ```

5. Set the following environment variables in the `.env` file:

   ```
      MONGODB_USERNAME=#value
      MONGODB_PASSWORD=#value
      MONGODB_NAME=#value
      MONGODB_PORT=#value
      MONGODB_HOST=#value
      HTTP_SERVER_PORT=#value
   ```

6. Run the following command to launch the server:
   ```
   docker-compose up --build
   ```

# phantom

Phone Number Hiding Tool

## Service setup

rename `example.env` to `.env` and fill in the data

use `honcho` or `foreman` to run the Procfile

## API Docs

`/numbers` returns json array of twilio numbers your account owns

`/start` begins the phantom call. It takes three params:

- twilioNum
- myNum
- targetNum

an example call: `/start?twilioNum=4151112222&targetNum=9253334444&myNum=5105556666`

`/connect` generates the needed TwiML to connect the call from the user to the target. Takes `num` param.

## Requirements

- java 1.8
- maven
- honcho or foreman to run a Procfile

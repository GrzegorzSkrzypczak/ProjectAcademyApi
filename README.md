Link for the simple working frontend:
https://pokemon-app-frontend-grzegorz.herokuapp.com/

If you want to see how the program is working, first create login and user in Postman.

Follow this 3 easy steps:

1) Create POST method in postman using the link below:
https://pokemon-academi-api-grzegorz.herokuapp.com/pokemons/signup

- in section "Body" select "raw", then go to "txt" section and choose "JSON" from the drop-down list. 
- in the empty box below please type (this is only an example, login and password is for you to decide):

{
    "login": "test@test.pl",
    "password": "test"
}

- press send.

2) Create another POST method in new window using the link below:
https://pokemon-academi-api-grzegorz.herokuapp.com/pokemons/login

Repeat the same steps as in 1st point:


- in section "Body" select "raw", then go to "txt" section and choose "JSON" from the drop-down list. 
- in the empty box below please type (login and password must be as created in 1st step):

{
    "login": "test@test.pl",
    "password": "test"
}

- press send.

3) Create GET method in Postman using the link below:
https://pokemon-academi-api-grzegorz.herokuapp.com/pokemons/list

One last time repeat all the steps:

- in section "Body" select "raw", then go to "txt" section and choose "JSON" from the drop-down list. 
- in the empty box below please type (login and password must be as created in 1st step):

{
    "login": "test@test.pl",
    "password": "test"
}

- press send.

NOW YOU CAN LOGIN AND ENJOY THE APPLICATION:
https://pokemon-app-frontend-grzegorz.herokuapp.com

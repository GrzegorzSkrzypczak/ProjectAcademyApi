Link for the simple working frontend:
https://pokemon-app-frontend-grzegorz.herokuapp.com/

If you want to see working program firs You have to create and login and user in Postman.

Fallow this 3 easy steps:

1) Create POST method in postman:
https://pokemon-academi-api-grzegorz.herokuapp.com/pokemons/signup

- in section Body select "raw", at the end right side press "text" and select "JSON"
- in empty window bellow type:

{
    "login": "test@test.pl",
    "password": "test"
}

- press send.

2) Create another POST method in new window (this time with login at the end):
https://pokemon-academi-api-grzegorz.herokuapp.com/pokemons/login

Repeat the same steps as in 1st point:


- in section Body select "raw", at the end right side press "text" and select "JSON"
- in empty window bellow type:

{
    "login": "test@test.pl",
    "password": "test"
}

- press send.

3) Create GET method in Postman:
https://pokemon-academi-api-grzegorz.herokuapp.com/pokemons/list

One last time repeat all the steps:

- in section Body select "raw", at the end right side press "text" and select "JSON"
- in empty window bellow type:

{
    "login": "test@test.pl",
    "password": "test"
}

- press send.

NOW YOU CAN LOGIN AND ENJOY THE APPLICATION:
https://pokemon-app-frontend-grzegorz.herokuapp.com

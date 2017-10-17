# SmartApp
# ToDo Application With Loal Storage

The ToDo application is created in ionic 2 , as well as this is made in order to help people learn and speed up their development process .

The application uses different ionic component as **ion-list,buttons as well as localstorage service**.

This application will help you to perform the CRUD operations  using the localstorageservice

The application contains two pages i.e HomePage which will list all the added ToDo's which are stored in localstorage and AddToDoPage which will allow you to save the todo in localstorage.

## Installations

To run the application we have to install some packages and plugins which are as follows:

``` sh
$ ionic cordova plugin add cordova-sqlite-storage

```
``` sh
$ npm install --save @ionic/storage
```

## Launching Application

To run the application on browser use following command

```sh

$ ionic serve
```

To run the application on your mobile device use the following command
```sh
$ ionic cordova run android
```

```sh
$ ionic cordova run ios
```

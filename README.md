## eurovision-votes

Eurovision song contest voting system

### Prerequisites
You will need to install java v1.8 and gradle, before you'll run app.
Please see bellow, the installation example for Linux OS 

```
//install java
sudo add-apt-repository ppa:webupd8team/java
sudo apt update
sudo apt install oracle-java8-installer
javac -version
//install gradle
sdk install gradle 4.7
//install git
apt-get install git
```

### Installing
Before you will use the app you will need to execute next commands:

```
git clone ${repo_url}
cd eurovision-votes
gradle clean
gradle eurovisionJar
```
### Running the tests

```
gradle testClasses
```

### Testing the app
Please, prepare the test data file with JSON like:
```
{'country':'Netherlands','votedFor':'Belgium'}
{'country':'Italy','votedFor':'Germany'}
...
```
Run next command to load data:
```
cd build/libs
java -jar eurovision-votes-all-1.0.jar load ${full_path_to_data_file} ${year}
```
Run next command to see the results:

```
java -jar eurovision-votes-all-1.0.jar results ${country} ${year}
```



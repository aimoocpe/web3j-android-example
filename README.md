# web3j-android-example

Let's say that you have a testnet and contract for test.

## Build Setup

``` sh
# install web3j
brew tap web3j/web3j
brew install web3j

# generate contract class from truffle project
web3j truffle generate <path/to/TutorialToken.json(ABI)> -o <path/to/java> -p <com.package>
```
**Note :** Another way to generate contract class [here](https://github.com/web3j/web3j#command-line-tools)

``` java

Web3j web3 = Web3jFactory.build(new HttpService("http://localhost:8545"));

// create credential by secret key (owner)
Credentials credential = Credentials.create("02d78849d22f79922a1e8b9e8767ac9ced43307862463415d6409f2c30a21a2b");

// get address owner
String owner = web3.ethAccounts().sendAsync().get().getAccounts().get(0);

// load contract by contract address
TutorialToken tutorial = TutorialToken.load("contract address: 0xc8b5f7d1e1af6f...",web3,credential,GAS_PRICE,GAS_LIMIT);

// get function balanceOf from contract
BigInteger result = tutorial.balanceOf(owner).sendAsync().get();

```

Documents Web3j [here](https://github.com/web3j/web3j)

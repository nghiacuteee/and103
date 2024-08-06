const mongoose = require("mongoose");
mongoose.set("strictQuery", true);

const localDB = "mongodb://localhost:27017/Assignment_AND103";

const connect = async () => {
  try {
    await mongoose.connect(localDB);
    console.log("connect OK");
  } catch (error) {
    console.log(error);
  }
};

module.exports = { connect };

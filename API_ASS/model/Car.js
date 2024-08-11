const mongoose = require("mongoose");

const Car = new mongoose.Schema(
  {
    ten: {
      type: String,
      require: true,
  },
  namSX: {
      type: Number,

  },
  hang: {
      type: String,
      require: true,
  },
  gia: {
      type: Number,

  },
  },
  {
    timestamps: true,
  }
);

module.exports = mongoose.model("car", Car);

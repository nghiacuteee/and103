const mongoose = require("mongoose");
const Scheme = mongoose.Schema;

const Category = new Scheme(
  {
    name: { type: String, require: true },
  },
  {
    timestamps: true,
  }
);

module.exports = mongoose.model("category", Category);

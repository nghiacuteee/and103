const mongoose = require("mongoose");
const Scheme = mongoose.Schema;

const Product = new Scheme(
  {
    name: { type: String },
    quantity: { type: Number },
    price: { type: Number },
    status: { type: Number },
    imageurl: { type: Array },
    description: { type: String },
    category_id: { type: Scheme.Types.ObjectId, ref: "category" },
  },
  {
    timestamps: true,
  }
);

module.exports = mongoose.model("product", Product);

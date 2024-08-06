const express = require("express");
const Category = require("../models/category");
const Product = require("../models/product");
const Upload = require("../config/common/upload");
const Account = require("../models/user");
const Bill = require("../models/bill");
const BillDetail = require("../models/billdetail");

var router = express.Router();

router.post("/add-category", async (req, res) => {
  try {
    const data = req.body;
    const newCate = new Category({
      name: data.name,
    });
    const result = await newCate.save();
    if (result) {
      res.json({
        status: 200,
        message: "Add cate OK",
        data: result,
      });
    } else {
      res.json({
        status: 400,
        message: "Add cate not OK",
        data: {},
      });
    }
  } catch (error) {
    console.log(error);
  }
});

router.get("/list-category", async (req, res) => {
  try {
    const data = await Category.find();
    if (data) {
      res.json({
        status: 200,
        message: "Category List",
        data: data,
      });
    } else {
      res.json({
        status: 200,
        message: "Not Category List",
        data: {},
      });
    }
  } catch (error) {
    console.log(error);
  }
});

router.post("/add-product", Upload.array("imageurl", 1), async (req, res) => {
  try {
    const data = req.body;
    const { files } = req;
    const urlImages = files.map(
      (file) => `${req.protocol}://10.0.2.2:3000/uploads/${file.filename}`
    );
    const newProduct = new Product({
      name: data.name,
      quantity: data.quantity,
      price: data.price,
      status: data.status,
      imageurl: urlImages[0],
      description: data.description,
      category_id: data.category_id,
    });
    const result = await newProduct.save();
    if (result) {
      res.json({
        status: 200,
        message: "Add product OK",
        data: result,
      });
    } else {
      res.json({
        status: 400,
        message: "Add product not OK",
        data: {},
      });
    }
  } catch (error) {
    console.log(error);
  }
});

router.post(
  "/update-product/:id",
  Upload.array("imageurl", 1),
  async (req, res) => {
    try {
      const { id } = req.params;
      const data = req.body;
      const { files } = req;
      const urlImages = files.map(
        (file) => `${req.protocol}://10.0.2.2:3000/uploads/${file.filename}`
      );
      const product = await Product.findById(id);
      let result = null;

      if (product) {
        (product.name = data.name ?? product.name),
          (product.quantity = data.quantity ?? product.quantity),
          (product.price = data.price ?? product.price),
          (product.status = data.status ?? product.status),
          (product.imageurl = urlImages[0] ?? product.imageurl),
          (product.description = data.description ?? product.description),
          (product.category_id = data.category_id ?? product.category_id);
        result = await product.save();
      }

      if (result) {
        res.json({
          status: 200,
          message: "Update product OK",
          data: result,
        });
      } else {
        res.json({
          status: 400,
          message: "Update product not OK",
          data: {},
        });
      }
    } catch (error) {
      console.log(error);
    }
  }
);

router.delete("/delete-product/:id", async (req, res) => {
  try {
    const { id } = req.params;
    const result = Product.findByIdAndDelete(id);
    if (result) {
      res.json({
        status: 200,
        message: "delete OK",
        data: result,
      });
    } else {
      res.json({
        status: 400,
        message: "delete NOT OK",
        data: {},
      });
    }
  } catch (error) {
    console.log(error);
  }
});

router.get("/list-products", async (req, res) => {
  try {
    const data = await Product.find();
    if (data) {
      res.json({
        status: 200,
        message: "Product list",
        data: data,
      });
    } else {
      res.json({
        status: 400,
        message: "Not Product list",
        data: {},
      });
    }
  } catch (error) {
    console.log(error);
  }
});

router.get("/list-products-by-type/:categoryid", async (req, res) => {
  try {
    const { categoryid } = req.params;
    const data = await Product.find({ category_id: categoryid });
    if (data) {
      res.json({
        status: 200,
        message: "Product list by category",
        data: data,
      });
    } else {
      res.json({
        status: 400,
        message: "Not Product list by category",
        data: {},
      });
    }
  } catch (error) {
    console.log(error);
  }
});

router.get("/get-product/:id", async (req, res) => {
  try {
    const { id } = req.params;
    const result = await Product.findById(id);
    if (result) {
      res.json({
        status: 200,
        message: "get product OK",
        data: result,
      });
    } else {
      res.json({
        status: 200,
        message: "get product NOT OK",
        data: {},
      });
    }
  } catch (error) {
    console.log(error);
  }
});

router.get("/get-product-by-name/:name", async (req, res) => {
  try {
    const { name } = req.params;
    const result = await Product.find({ name: name });
    if (result) {
      res.json({
        status: 200,
        message: "get product by name OK",
        data: result,
      });
    } else {
      res.json({
        status: 200,
        message: "get product by name NOT OK",
        data: {},
      });
    }
  } catch (error) {
    console.log(error);
  }
});

router.post("/sign-up", Upload.array("avatar"), async (req, res) => {
  try {
    console.log(req.body);
    const data = req.body;
    const { files } = req;
    // const avatarURI = files.map((file)=>`${req.protocol}://10.0.2.2:3000/uploads/${file.filename}`)
    const newUser = new Account({
      // username: data.username,
      email: data.email,
      name: data.name,
      pass: data.pass,
      // avatar: avatarURI[0],
      // available: data.available
    });
    const result = await newUser.save();
    if (result) {
      res.json({
        status: 200,
        message: "Đăng Ký Thành Công",
        data: {},
      });
    } else {
      res.json({
        status: 400,
        message: "Đăng Ký Thành Bại",
        data: {},
      });
    }
  } catch (error) {
    console.log(error);
  }
});

router.post("/login", async (req, res) => {
  try {
    const { email, pass } = req.body;
    console.log(req.body);
    const result = await Account.findOne({ email, pass });
    if (result) {
      res.json({
        status: 200,
        message: "OK",
        data: { ...result._doc, pass: "" },
      });
    } else {
      res.json({
        status: 400,
        message: "OK",
        data: {},
      });
    }
  } catch (error) {
    console.log(error);
  }
});

router.put(
  "/update-account/:id",
  Upload.array("avatar", 1),
  async (req, res) => {
    try {
      const { id } = req.params;
      const data = req.body;
      const { files } = req;
      const avatarURI = files.map(
        (file) => `${req.protocol}://10.0.2.2:3000/uploads/${file.filename}`
      );
      const account = await Account.findById(id);
      console.log(avatarURI[0]);
      let result = null;
      if (account) {
        account.email = data.email ?? account.email;
        account.name = data.name ?? account.name;
        account.pass = account.pass;
        account.avatar = avatarURI[0];
        account.available = data.available ?? account.available;
        result = await account.save();
      }
      if (result) {
        res.json({
          status: 200,
          message: "update account OK",
          data: {},
        });
      } else {
        res.json({
          status: 400,
          message: "update account NOT OK",
          data: {},
        });
      }
    } catch (error) {
      console.log(error);
    }
  }
);

router.get("/get-bill-list", async (req, res) => {
  try {
    const data = await Bill.find();
    if (data) {
      res.json({
        status: 200,
        message: "List Bill",
        data: data,
      });
    } else {
      res.json({
        status: 400,
        message: "Not List Bill",
        data: {},
      });
    }
  } catch (error) {
    console.log(error);
  }
});

router.post("/add-bill", async (req, res) => {
  try {
    const data = req.body;
    const newBill = new Bill({
      email: data.email,
    });
    const result = await newBill.save();
    if (result) {
      res.json({
        status: 200,
        message: "Add Bill OK",
        data: result,
      });
    } else {
      res.json({
        status: 400,
        message: "Add NOT Bill OK",
        data: {},
      });
    }
  } catch (error) {
    console.log(error);
  }
});

router.get("/get-billdetail-list", async (req, res) => {
  try {
    const data = await BillDetail.find();
    if (data) {
      res.json({
        status: 200,
        message: "List Bill Detail",
        data: data,
      });
    } else {
      res.json({
        status: 400,
        message: "Not List Bill Detail",
        data: {},
      });
    }
  } catch (error) {
    console.log(error);
  }
});

router.post("/add-bill-detail", async (req, res) => {
  try {
    const data = req.body;
    const newBillDetail = new BillDetail({
      billid: data.billid,
      productid: data.productid,
      quantity: data.quantity,
    });
    const result = await newBillDetail.save();
    if (result) {
      res.json({
        status: 200,
        message: "Add Bill Detail OK",
        data: result,
      });
    } else {
      res.json({
        status: 400,
        message: "Add Bill Detail NOT OK",
        data: {},
      });
    }
  } catch (error) {
    console.log(error);
  }
});

router.put("/update-bill-detail/:id", async (req, res) => {
  try {
    const { id } = req.params;
    const data = req.body;
    const billdetail = Product.findById(id);
    let result = null;
    if (billdetail) {
      billdetail.billid = billdetail.billid;
      billdetail.productid = billdetail.productid;
      billdetail.quantity = data.quantity ?? billdetail.quantity;
      result = await billdetail.save();
    }
    if (result) {
      res.json({
        status: 200,
        message: "Update Detail OK",
        data: result,
      });
    } else {
      res.json({
        status: 400,
        message: "Update Detail NOT OK",
        data: {},
      });
    }
  } catch (error) {
    console.log(error);
  }
});

router.delete("/delete-bill-detail/:id", async (req, res) => {
  try {
    const { id } = req.params;
    const result = Product.findById(id);
    if (result) {
      res.json({
        status: 200,
        message: "delete detail OK",
        data: result,
      });
    } else {
      res.json({
        status: 400,
        message: "delete detail NOT OK",
        data: {},
      });
    }
  } catch (error) {
    console.log(error);
  }
});

module.exports = router;

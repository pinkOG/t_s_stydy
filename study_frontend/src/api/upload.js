const express = require('express');
const multer = require('multer');
const path = require('path');
const cors = require('cors');
const { log } = require('console');

const app = express();

app.use(cors());
app.use(express.json());
app.use('/uploads', express.static(path.join(__dirname, 'uploads')));

const storage = multer.diskStorage({
  destination: function (req, file, cb) {
    cb(null, 'uploads/');
  },
  filename: function (req, file, cb) {
    cb(null, Date.now() + '-' + file.originalname);
  }
});

const upload = multer({ storage: storage });

// 上传作业
app.post('/api/uploadAssignment', upload.single('file'), (req, res) => {
  const file = req.file;
  if (!file) {
    return res.status(400).send('没有文件上传');
  }
  console.log('Uploads file:',file);
  res.json({ url: `http://localhost:3000/uploads/${file.filename}` });
});

// 启动服务器
app.listen(3000, () => {
  console.log('Server running on port 3000');
});
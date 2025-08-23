import axios from "axios";

const api = axios.create({
  baseURL: "", // 백엔드 서버 주소
});

export default api;
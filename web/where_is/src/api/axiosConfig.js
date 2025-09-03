import axios from 'axios';

const BASE_URL = import.meta.env.VITE_API_BASE_URL;

// 기본 설정
const api = axios.create({
  baseURL: BASE_URL,
  withCredentials: true,
  timeout: 10000, // 10초로 변경
});

// 응답 인터셉터 설정
api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.status === 401) {
      // 토큰 만료 시 자동 로그아웃 처리
      logoutAndRedirect();
    }
    return Promise.reject(error);
  }
);

// 로그아웃 및 리다이렉트 함수
export async function logoutAndRedirect() {
  try {
    // 로그아웃 API 호출 (필요시)
    const res = await fetch(`${BASE_URL}/auth/logout`, { 
      method: "POST",
      headers: { "Content-Type": "application/json" },
      credentials: 'include' 
    });
  } catch (error) {
    console.error('Logout API call failed:', error);
  } finally {
    // 쿠키 제거
    //document.cookie = 'token=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;';
    //document.cookie = 'refreshToken=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;';
    
    // 로그인 페이지로 
    window.location.href = '/login';
  }
};

export default api; 
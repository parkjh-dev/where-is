import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import fs from 'fs'
import path from 'path'

// https://vite.dev/config/
export default defineConfig(({ command, mode }) => {
  const serverConfig = {
    port: 3001,
    host: '0.0.0.0'
  }

  // SSL 인증서 파일이 있으면 HTTPS 사용
  try {
    const keyPath = path.resolve(__dirname, 'localhost+2-key.pem')
    const certPath = path.resolve(__dirname, 'localhost+2.pem')
    
    if (fs.existsSync(keyPath) && fs.existsSync(certPath)) {
      serverConfig.https = {
        key: fs.readFileSync(keyPath),
        cert: fs.readFileSync(certPath)
      }
      console.log('HTTPS 모드로 실행됩니다.')
    } else {
      console.warn('SSL 인증서 파일을 찾을 수 없습니다. HTTP로 실행됩니다.')
    }
  } catch (error) {
    console.warn('SSL 인증서 로드 실패:', error.message)
  }

  return {
    server: serverConfig,
    plugins: [react()],
  }
})

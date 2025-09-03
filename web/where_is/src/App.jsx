import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import './App.css'
import BasicLayout from './components/layout/BasicLayout'
import LoginEmail from './pages/auth/LoginEmail'
import LoginMain from './pages/auth/LoginMain'
import NotFound from './pages/NotFound'
import Main from './pages/main'
import Detail from './pages/Detail'
import DetailSummary from './pages/DetailSummary'

function App() {
  return (

      <Router>
        <Routes>
          {/* 일반 페이지 레이아웃 */}
          <Route element={<BasicLayout />}>
            <Route path="/auth/login/main" element={<LoginMain />} />
            <Route path="/auth/login/email" element={<LoginEmail />} />
            <Route path="/" element={<Main />} />
            <Route path="/smoking-area/:id" element={<Detail />} />
            <Route path="/smoking-area/:id/summary" element={<DetailSummary />} />
          </Route>
          {/* 404 페이지 - 정의되지 않은 모든 경로 */}
          <Route path="*" element={<NotFound />} />
        </Routes>
      </Router>

  )
}

export default App
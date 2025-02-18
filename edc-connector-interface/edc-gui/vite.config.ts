import react from '@vitejs/plugin-react-swc'
import { defineConfig } from 'vite'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  base: "/edc/",
  server: {
    proxy: {
        "/edc-provider": {
            target: "http://localhost:8081/edc-provider",
            changeOrigin: true,
            rewrite: (path) => path.replace(/^\/edc-provider/, ''),
        },
        "/edc-consumer": {
            target: "http://localhost:8081/edc-consumer",
            changeOrigin: true,
            rewrite: (path) => path.replace(/^\/edc-consumer/, ''),
        }
    }
  }
})

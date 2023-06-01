/* const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true
}) */

module.exports = {
  outputDir: "../src/main/resources/static",
  devServer: {
    port: 8083,
    proxy: {
      '/api': {
        target: 'http://localhost:8081',
        changeOrigin: true
      },
    },
    client: {
      overlay: true,
      // 웹소켓 설정
      webSocketURL: { hostname: undefined, pathname: undefined, port: '0' },
    }
  }
};
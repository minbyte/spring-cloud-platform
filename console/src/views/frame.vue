<template>
  <div class="frame-container">
    <iframe :src="iframeUrl" width="100%" height="100%" frameborder="0" scrolling="yes"/>
  </div>
</template>


<script>
  export default {
    props: {
      iframeUrl: {
        type: String,
        default: ''
      }
    }
  }

</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .frame-container{
    width: 100%;height: 100%;position: absolute;left: 0;top: 0;
  }
</style>

<template>
    <div class="mb-3 accordion" id="accordionExample">
        <div class="accordion-item">
            <h2 class="accordion-header" id="headingTwo">
                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                   글 목록 보기
                </button>
            </h2>
            <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
                <div class="accordion-body">
                    요기다가 list
                </div>
            </div>
        </div>
    </div>
    <div class="mb-3 card">
        <div class="card-body">
            <ul class="list-group list-group-flush">
                <li class="list-group-item">
                    <h5 class="mb-3 card-title text-start">제목</h5>
                    <h6 class="mb-4 card-subtitle text-muted text-start">
                        게시 날짜
                    </h6>
                </li>
                <li class="p-4 list-group-item">
                    콘텐츠
                </li>
                <li class="mb-0 list-group-item">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-center">
                            <a class="page-link" href="#" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                            <li class="page-item"><a class="page-link" href="#">1</a></li>
                            <li class="page-item"><a class="page-link" href="#">2</a></li>
                            <li class="page-item"><a class="page-link" href="#">3</a></li>
                            <li class="page-item">
                                <a class="page-link" href="#" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </li>
            </ul>
        </div>
    </div>
</template>

<script>
export default {

}
</script>

<style>

</style>
<template>
    <h4>Industry Page</h4>
    <div class="row">
        <div class="col-md-8 col-lg-9">
            <div class="accordion mb-3" id="accordionExample">
                <div class="accordion-item">
                    <h2 class="accordion-header">
                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne"
                            aria-expanded="true" aria-controls="collapseOne">
                            Add Industry
                        </button>
                    </h2>
                    <div id="collapseOne" class="accordion-collapse collapse" data-bs-parent="#accordionExample">
                        <div class="accordion-body">
                            <div class="mb-3">
                                <label>First Name</label>
                                <input type="text" class="form-control" />
                            </div>

                            <div class="mb-3">
                                <label>Last Name</label>
                                <input type="text" class="form-control" />
                            </div>

                            <div class="mb-3">
                                <label>Email</label>
                                <input type="email" class="form-control" />
                            </div>

                            <div class="mb-3">
                                <label>Gender</label>
                                <select class="form-control">
                                    <option value="" disabled>Select</option>
                                    <option>Male</option>
                                    <option>Female</option>
                                    <option>Other</option>
                                </select>
                            </div>

                            <div class="mb-3">
                                <label>Date of Birth</label>
                                <input type="date" class="form-control" />
                            </div>

                            <div class="mb-3">
                                <label>Salary</label>
                                <input type="number" class="form-control" />
                            </div>
                            <button class="btn btn-primary me-2">Save</button>
                            <button class="btn btn-primary">Cancel</button>
                        </div>
                    </div>
                </div>
            </div>
            <div>
                <h5>Industry List</h5>
                <table class="table table-hover table-striped table-bordered">
                    <thead class="table-primary">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">First</th>
                            <th scope="col">Last</th>
                            <th scope="col">Handle</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th scope="row">1</th>
                            <td>Mark</td>
                            <td>Otto</td>
                            <td>@mdo</td>
                        </tr>
                        <tr>
                            <th scope="row">2</th>
                            <td>Jacob</td>
                            <td>Thornton</td>
                            <td>@fat</td>
                        </tr>
                        <tr>
                            <th scope="row">3</th>
                            <td>John</td>
                            <td>Doe</td>
                            <td>@social</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="col-md-4 col-lg-3">
            <h5>Edit Industry</h5>
            <hr>
            <div class="mb-3">
                <label>First Name</label>
                <input type="text" class="form-control" />
            </div>

            <div class="mb-3">
                <label>Last Name</label>
                <input type="text" class="form-control" />
            </div>

            <div class="mb-3">
                <label>Email</label>
                <input type="email" class="form-control" />
            </div>

            <div class="mb-3">
                <label>Gender</label>
                <select class="form-control">
                    <option value="" disabled>Select</option>
                    <option>Male</option>
                    <option>Female</option>
                    <option>Other</option>
                </select>
            </div>

            <div class="mb-3">
                <label>Date of Birth</label>
                <input type="date" class="form-control" />
            </div>

            <div class="mb-3">
                <label>Salary</label>
                <input type="number" class="form-control" />
            </div>
            <button class="btn btn-primary float-end">Update</button>
        </div>
    </div>
</template>
<script>
export default {
    name: "IndustryPage",
}
</script>
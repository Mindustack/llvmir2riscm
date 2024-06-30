; ModuleID = 'Other_statements.c'
source_filename = "Other_statements.c"
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"

; Function Attrs: noinline nounwind optnone sspstrong uwtable
define dso_local i32 @main() local_unnamed_addr #0 {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  %4 = alloca i32, align 4
  %5 = alloca i32, align 4
  store i32 0, ptr %1, align 4
  call void @llvm.lifetime.start.p0(i64 4, ptr %2) #2
  call void @llvm.lifetime.start.p0(i64 4, ptr %3) #2
  call void @llvm.lifetime.start.p0(i64 4, ptr %4) #2
  call void @llvm.lifetime.start.p0(i64 4, ptr %5) #2
  store i32 10, ptr %2, align 4, !tbaa !5
  store i32 4, ptr %4, align 4, !tbaa !5
  store i32 9, ptr %5, align 4, !tbaa !5
  br label %6

6:                                                ; preds = %11, %0
  %7 = load i32, ptr %2, align 4, !tbaa !5
  %8 = add nsw i32 %7, -1
  store i32 %8, ptr %2, align 4, !tbaa !5
  %9 = load i32, ptr %2, align 4, !tbaa !5
  %10 = icmp sgt i32 %9, 5
  br i1 %10, label %11, label %12

11:                                               ; preds = %6
  br label %6

12:                                               ; preds = %6
  %13 = load i32, ptr %5, align 4, !tbaa !5
  switch i32 %13, label %23 [
    i32 6, label %14
    i32 5, label %17
    i32 4, label %20
  ]

14:                                               ; preds = %12
  %15 = load i32, ptr %5, align 4, !tbaa !5
  %16 = add nsw i32 %15, -1
  store i32 %16, ptr %5, align 4, !tbaa !5
  br label %17

17:                                               ; preds = %12, %14
  %18 = load i32, ptr %5, align 4, !tbaa !5
  %19 = add nsw i32 %18, -1
  store i32 %19, ptr %5, align 4, !tbaa !5
  br label %20

20:                                               ; preds = %12, %17
  %21 = load i32, ptr %5, align 4, !tbaa !5
  %22 = add nsw i32 %21, -1
  store i32 %22, ptr %5, align 4, !tbaa !5
  br label %23

23:                                               ; preds = %12, %20
  store i32 15, ptr %5, align 4, !tbaa !5
  br label %24

24:                                               ; preds = %23
  call void @llvm.lifetime.end.p0(i64 4, ptr %5) #2
  call void @llvm.lifetime.end.p0(i64 4, ptr %4) #2
  call void @llvm.lifetime.end.p0(i64 4, ptr %3) #2
  call void @llvm.lifetime.end.p0(i64 4, ptr %2) #2
  ret i32 0
}

; Function Attrs: mustprogress nocallback nofree nosync nounwind willreturn memory(argmem: readwrite)
declare void @llvm.lifetime.start.p0(i64 immarg, ptr nocapture) #1

; Function Attrs: mustprogress nocallback nofree nosync nounwind willreturn memory(argmem: readwrite)
declare void @llvm.lifetime.end.p0(i64 immarg, ptr nocapture) #1

attributes #0 = { noinline nounwind optnone sspstrong uwtable "min-legal-vector-width"="0" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cmov,+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "tune-cpu"="generic" }
attributes #1 = { mustprogress nocallback nofree nosync nounwind willreturn memory(argmem: readwrite) }
attributes #2 = { nounwind }

!llvm.module.flags = !{!0, !1, !2, !3}
!llvm.ident = !{!4}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{i32 8, !"PIC Level", i32 2}
!2 = !{i32 7, !"PIE Level", i32 2}
!3 = !{i32 7, !"uwtable", i32 2}
!4 = !{!"clang version 17.0.6"}
!5 = !{!6, !6, i64 0}
!6 = !{!"int", !7, i64 0}
!7 = !{!"omnipotent char", !8, i64 0}
!8 = !{!"Simple C/C++ TBAA"}
